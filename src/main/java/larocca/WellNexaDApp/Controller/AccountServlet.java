package larocca.WellNexaDApp.Controller;

import larocca.WellNexaDApp.Model.Operatore.Operatore;
import larocca.WellNexaDApp.Model.Operatore.OperatoreDAO;
import larocca.WellNexaDApp.Model.Paziente.Paziente;
import larocca.WellNexaDApp.Model.Paziente.PazienteDAO;
import larocca.WellNexaDApp.Utilities.FormExtractor;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.FastRawTransactionManager;
import org.web3j.tx.gas.StaticGasProvider;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AccountServlet", urlPatterns = "/AccountServlet/*")
public class AccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pattern = (request.getPathInfo() == null ? "/" : request.getPathInfo());
        HttpSession session = request.getSession();
        String path = null;

        switch (pattern){
            case "/login_page":
                path = "/WEB-INF/jsp/Login.jsp";
                break;
            case "/registration_page":
                path = "/WEB-INF/jsp/Registrazione.jsp";
                break;
            case "/logout_page":
                session.invalidate();
                path = "/index.jsp";
                break;
        }

        RequestDispatcher rd = request.getRequestDispatcher(path);
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pattern = (request.getPathInfo() == null ? "/" : request.getPathInfo());
        String path = null;
        OperatoreDAO operatoreDAO = new OperatoreDAO();
        PazienteDAO pazienteDAO = new PazienteDAO();
        Map<String, String[]> map = request.getParameterMap();
        HttpSession session = request.getSession();
        String option = request.getParameter("tipoUtente");

        String nodeUrl = "https://nd-257-258-415.p2pify.com/b3811d31b8bd0db395a594407ff4eb5c";
        String contractAddress = "0xd985A34450F1e804Ae48A179628Feda5832790E4";
        String privateKey = "296d2295d7b2bdf85e3b40926a28742b1996772230c81fcde6ff1604c2d0689a";
        String address = "0xCD87a137C191Bd24355B427dFAf7a91a17A9360A";

        switch (pattern){
            case "/login":

                Paziente p = (Paziente) FormExtractor.extractLogin(map);

                // TODO: 03/12/2023
                if(option == null){
                    path = "/WEB-INF/jsp/Login.jsp";
                } else if(option.equals("paziente")) {
                    if (pazienteDAO.checkLogin(p.getEmail(), p.getPasswordhash())){

                        p = pazienteDAO.doRetrieveByEmail(p.getEmail());
                        session.setAttribute("user", p);

                        Web3j web3j = Web3j.build(new HttpService(nodeUrl));
                        Credentials credentials = Credentials.create(privateKey);
                        FastRawTransactionManager txManager = new FastRawTransactionManager(web3j, credentials, 11155111);
                        MedicalRecord contract = MedicalRecord.load(contractAddress, web3j, txManager, new StaticGasProvider(BigInteger.valueOf(30_100_000_000L), BigInteger.valueOf(9_000_000)));

                        try {
                            List<MedicalRecord.Examination> examinations = contract.getExaminationListByAddress(p.getIndirizzo()).send();
                            session.setAttribute("lista_visite", examinations);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        path = "/WEB-INF/jsp/CartellaPaziente.jsp";
                    } else {
                        path = "/WEB-INF/jsp/Login.jsp";
                    }
                } else if(option.equals("operatore")) {
                    if (operatoreDAO.checkLogin(p.getEmail(), p.getPasswordhash())){
                        session.setAttribute("user", p.getEmail());
                        path = "/WEB-INF/jsp/RicercaPaziente.jsp";
                    } else {
                        path = "/WEB-INF/jsp/Login.jsp";
                    }
                } else {
                    path = "/WEB-INF/jsp/PaginaErrore.jsp";
                }

                break;
            case "/registrazione":

                if(option.equals("paziente")) {
                    Paziente paziente = FormExtractor.extractRegistrationPaziente(map);
                    if(!pazienteDAO.checkRegistration(paziente)) {
                        pazienteDAO.doSave(paziente);
                        path = "/WEB-INF/jsp/RegistrazioneCompletata.jsp";
                    }else {
                        request.setAttribute("messaggioErrore", "Registrazione fallita. Si prega di riprovare.");
                        path = "/WEB-INF/jsp/Registrazione.jsp";
                    }
                } else if(option.equals("operatore")) {
                    Operatore operatore = FormExtractor.extractRegistrationOperatore(map);

                    if(operatoreDAO.doRetrieveAll() == null) {

                        Web3j web3j = Web3j.build(new HttpService(nodeUrl));
                        Credentials credentials = Credentials.create(privateKey);
                        FastRawTransactionManager txManager = new FastRawTransactionManager(web3j, credentials, 11155111);
                        MedicalRecord contract = MedicalRecord.load(contractAddress, web3j, txManager, new StaticGasProvider(BigInteger.valueOf(30_100_000_000L), BigInteger.valueOf(9_000_000)));

                        try {
                            contract.addOperator(address).send();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if(!operatoreDAO.checkRegistration(operatore)) {
                        operatoreDAO.doSave(operatore);
                        path = "/WEB-INF/jsp/RegistrazioneCompletata.jsp";
                    }else {
                        request.setAttribute("messaggioErrore", "Registrazione fallita. Si prega di riprovare.");
                        path = "/WEB-INF/jsp/Registrazione.jsp";
                    }
                } else {
                    path = "/WEB-INF/jsp/PaginaErrore.jsp";
                }
                break;
        }

        RequestDispatcher rd = request.getRequestDispatcher(path);
        rd.forward(request,response);
    }
}
