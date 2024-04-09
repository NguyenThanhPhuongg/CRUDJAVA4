package servlet;

import entity.NXB;
import entity.Sach;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.hibernate.Session;
import repository.PageRepo;
import repository.SachRepo;
import utils.HibernateUtils;

import java.io.IOException;
import java.util.List;

@WebServlet({
        "/sach/index",
        "/sach/store",
        "/sach/create",
        "/sach/delete"
})
public class SachServlet extends HttpServlet {
    SachRepo sachRepo = new SachRepo();
    PageRepo pageRepo = new PageRepo();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if(uri.contains("index")) {
            this.index(request,response);
        } else if (uri.contains("create")) {
            this.create(request,response);
        } else if (uri.contains("delete")) {
            this.delete(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if(uri.contains("store")) {
            this.store(request,response);
        }
    }
    private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<NXB> listNXB = sachRepo.getNXB();
        request.setAttribute("nxb",listNXB);
        request.getRequestDispatcher("/views/create.jsp").forward(request,response);
    }

    private void store(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        Integer id = Integer.parseInt(request.getParameter("id"));
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        Integer idNXB = Integer.parseInt(request.getParameter("idNXB"));
        if(ma.equals("") || ten.equals("")) {
            request.setAttribute("error","Khong duoc de trong du lieu");
            request.getRequestDispatcher("/views/create.jsp").forward(request,response);
        }
        Sach sach = Sach.builder()
                    .ma(ma)
                    .ten(ten)
                    .idNXB(idNXB)
                    .build();
        sachRepo.insert(sach);
        response.sendRedirect("/sach/index");
    }
    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("idSach");
//        try(Session session = HibernateUtils.getSessionFactory().openSession()){
//            Sach sach = session.get(Sach.class, id);
//            sachRepo.delete(sach);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        Sach sach = sachRepo.findById(Integer.parseInt(id));
        sachRepo.delete(sach);
        response.sendRedirect("/sach/index");

    }
    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNumber = 1;
        int pageSize = 3;
        if(request.getParameter("page") != null) {
            pageNumber = Integer.parseInt(request.getParameter("page"));
        }
        long tutolPage = pageRepo.getTutolPage(pageSize);
        List<Sach> list = pageRepo.getAllSach(pageNumber, pageSize);
        request.setAttribute("sachList",list);
        request.setAttribute("tutolPage",tutolPage);
        request.setAttribute("curentPage",pageNumber);

//        List<Sach> ds = sachRepo.getSach();
//        request.setAttribute("data", ds);
        request.getRequestDispatcher("/views/index.jsp").forward(request,response);

    }

}