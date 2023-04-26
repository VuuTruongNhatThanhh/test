package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.Dao.BillDao;
import vn.edu.hcmuaf.fit.model.Bills;
import vn.edu.hcmuaf.fit.model.User;
import vn.edu.hcmuaf.fit.services.PermissionService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RevenueWeek", value = "/RevenueWeek")
public class RevenueWeek extends HttpServlet {
    private static  String name = "home";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Bills> bd = BillDao.getInstance().DeliverBillWeek();
        request.setAttribute("bd", bd);

        if(request.getSession().getAttribute("auth")==null){
            response.sendRedirect("/errorAccessUser.jsp");
            return;
        }
        int per = PermissionService.getInstance().checkAccess(name, ((User)(request.getSession().getAttribute("auth"))).getId());

        if(per==2) {
            response.sendRedirect("/errorAccessUser.jsp");
            return;
        }

        request.getRequestDispatcher("AdminWeb/revenueWeek.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
