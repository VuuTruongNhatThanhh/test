package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.LogDao;
import vn.edu.hcmuaf.fit.Dao.PreferentialDao;
import vn.edu.hcmuaf.fit.bean.Log;
import vn.edu.hcmuaf.fit.model.Preferential;
import vn.edu.hcmuaf.fit.model.User;
import vn.edu.hcmuaf.fit.services.PermissionService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PreferentialAdmin", value = "/PreferentialAdmin")
public class PreferentialAdmin extends HttpServlet {
    private static  String name = "code";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Preferential> pr = PreferentialDao.getInstance().getAll();
        request.setAttribute("pr", pr);

        if(request.getSession().getAttribute("auth")==null){
            response.sendRedirect("/errorAccessUser.jsp");
            return;
        }
        int per = PermissionService.getInstance().checkAccess(name, ((User)(request.getSession().getAttribute("auth"))).getId());

        if(per==2) {
            response.sendRedirect("/errorAccessUser.jsp");
            return;
        }



        request.getRequestDispatcher("AdminWeb/preferential.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
