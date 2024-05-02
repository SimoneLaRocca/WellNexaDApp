package larocca.WellNexaDApp.Controller;

import larocca.WellNexaDApp.Model.Operatore.OperatoreDAO;
import larocca.WellNexaDApp.Model.Paziente.Paziente;
import larocca.WellNexaDApp.Model.Paziente.PazienteDAO;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.FastRawTransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

@WebServlet(name = "OperatoreServlet", urlPatterns = "/OperatoreServlet/*")
public class OperatoreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PazienteDAO pazienteDAO = new PazienteDAO();
        HttpSession session = request.getSession();

        String codiceFiscale = request.getParameter("codiceFiscale");

        Paziente paziente = pazienteDAO.doRetrieveByCodiceFiscale(codiceFiscale);

        String nodeUrl = "https://nd-257-258-415.p2pify.com/b3811d31b8bd0db395a594407ff4eb5c";
        String contractAddress = "0xd985A34450F1e804Ae48A179628Feda5832790E4";
        String privateKey = "296d2295d7b2bdf85e3b40926a28742b1996772230c81fcde6ff1604c2d0689a";
        Web3j web3j = Web3j.build(new HttpService(nodeUrl));
        Credentials credentials = Credentials.create(privateKey);
        FastRawTransactionManager txManager = new FastRawTransactionManager(web3j, credentials, 11155111);

        MedicalRecord contract = MedicalRecord.load(contractAddress, web3j, txManager, new StaticGasProvider(BigInteger.valueOf(30_100_000_000L), BigInteger.valueOf(9_000_000)));

        try {
            List<MedicalRecord.Examination> examinations = contract.getExaminationListByAddress(paziente.getIndirizzo()).send();
            session.setAttribute("lista_visite", examinations);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("paziente", paziente);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AggiornaPaziente.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pattern = (request.getPathInfo() == null ? "/" : request.getPathInfo());
        String path = null;
        PazienteDAO pazienteDAO = new PazienteDAO();
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, String> parameters = new HashMap<>();
        HttpSession session = request.getSession();
        String codice_fiscale;
        Paziente p;

        switch (pattern) {
            case "/search":
                ArrayList<Paziente> pazienti = (ArrayList<Paziente>) pazienteDAO.search(parameters, parameterMap);
                request.setAttribute("pazienti", pazienti);
                path = "/WEB-INF/jsp/RicercaPaziente.jsp";
                break;
            case "/aggiungi_visita":
                codice_fiscale = request.getParameter("codice_fiscale");
                p = pazienteDAO.doRetrieveByCodiceFiscale(codice_fiscale);

                String esame = request.getParameter("esame");
                String codice = request.getParameter("codiceVisita");
                String nota = request.getParameter("nota");

                String nodeUrl = "https://nd-257-258-415.p2pify.com/b3811d31b8bd0db395a594407ff4eb5c";
                String contractAddress = "0xd985A34450F1e804Ae48A179628Feda5832790E4";
                String privateKey = "296d2295d7b2bdf85e3b40926a28742b1996772230c81fcde6ff1604c2d0689a";

                Web3j web3j = Web3j.build(new HttpService(nodeUrl));
                Credentials credentials = Credentials.create(privateKey);
                FastRawTransactionManager txManager = new FastRawTransactionManager(web3j, credentials, 11155111);
                MedicalRecord contract = MedicalRecord.load(contractAddress, web3j, txManager, new StaticGasProvider(BigInteger.valueOf(30_100_000_000L), BigInteger.valueOf(9_000_000)));

                try {
                    contract.addExamination(p.getIndirizzo(), codice, esame, nota).send();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                path = "/WEB-INF/jsp/AggiornaPaziente.jsp";
                break;
            case "/modifica_dati":
                String address = request.getParameter("indirizzo");
                codice_fiscale = request.getParameter("codice_fiscale");
                p = pazienteDAO.doRetrieveByCodiceFiscale(codice_fiscale);

                pazienteDAO.updatePaziente(p, address);

                path = "/WEB-INF/jsp/AggiornaPaziente.jsp";
                break;
        }

        RequestDispatcher rd = request.getRequestDispatcher(path);
        rd.forward(request,response);
    }
}
