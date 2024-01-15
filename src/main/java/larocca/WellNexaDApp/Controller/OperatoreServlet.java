package larocca.WellNexaDApp.Controller;

import larocca.WellNexaDApp.Model.Operatore.OperatoreDAO;
import larocca.WellNexaDApp.Model.Paziente.Paziente;
import larocca.WellNexaDApp.Model.Paziente.PazienteDAO;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "OperatoreServlet", urlPatterns = "/OperatoreServlet/*")
public class OperatoreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OperatoreDAO operatoreDAO = new OperatoreDAO();
        PazienteDAO pazienteDAO = new PazienteDAO();
        HttpSession session = request.getSession();

        String codiceFiscale = request.getParameter("codiceFiscale");

        Paziente paziente = pazienteDAO.doRetrieveByCodiceFiscale(codiceFiscale);
        request.setAttribute("paziente", paziente);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AggiornaPaziente.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pattern = (request.getPathInfo() == null ? "/" : request.getPathInfo());
        String path = null;
        OperatoreDAO operatoreDAO = new OperatoreDAO();
        PazienteDAO pazienteDAO = new PazienteDAO();
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, String> parameters = new HashMap<>();
        HttpSession session = request.getSession();

        switch (pattern) {
            case "/search":
                ArrayList<Paziente> pazienti = (ArrayList<Paziente>) pazienteDAO.search(parameters, parameterMap);
                request.setAttribute("pazienti", pazienti);
                path = "/WEB-INF/jsp/RicercaPaziente.jsp";
                break;
            case "/aggiungi_visita":
                String codice_fiscale = request.getParameter("codice_fiscale");
                Paziente p = pazienteDAO.doRetrieveByCodiceFiscale(codice_fiscale);

                String esame = request.getParameter("esame");
                String codice = request.getParameter("codiceVisita");
                String nota = request.getParameter("nota");

                String nodeUrl = "https://rpc.sepolia.org/";  // Sostituisci con l'effettivo URL del nodo Sepolia

                String contractAddress = "0x115fb129C0763ADF70E0ED4548E193695c64EaF5";  // Sostituisci con l'effettivo indirizzo del contratto

                String privateKey = "296d2295d7b2bdf85e3b40926a28742b1996772230c81fcde6ff1604c2d0689a";  // Sostituisci con la tua chiave privata

                Web3j web3j = Web3j.build(new HttpService(nodeUrl));

                Credentials credentials = Credentials.create(privateKey);

                MedicalRecord contract = MedicalRecord.load(contractAddress, web3j, credentials, new DefaultGasProvider());

                contract.addExamination(p.getIndirizzo(), codice, esame, nota);

                path = "/WEB-INF/jsp/AggiornaPaziente.jsp";
                break;
        }


        RequestDispatcher rd = request.getRequestDispatcher(path);
        rd.forward(request,response);
    }
}
