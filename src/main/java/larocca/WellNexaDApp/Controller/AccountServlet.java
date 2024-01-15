package larocca.WellNexaDApp.Controller;

import larocca.WellNexaDApp.Model.Operatore.Operatore;
import larocca.WellNexaDApp.Model.Operatore.OperatoreDAO;
import larocca.WellNexaDApp.Model.Paziente.Paziente;
import larocca.WellNexaDApp.Model.Paziente.PazienteDAO;
import larocca.WellNexaDApp.Utilities.FormExtractor;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

        switch (pattern){
            case "/login":

                Paziente p = (Paziente) FormExtractor.extractLogin(map);

                // TODO: 03/12/2023
                if(option.equals("paziente")) {
                    if (pazienteDAO.checkLogin(p.getEmail(), p.getPasswordhash())){
                        session.setAttribute("user", p.getEmail());
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
