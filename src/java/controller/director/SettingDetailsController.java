/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.director;

import controller.TypeConfigController;
import dal.SettingDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Setting;
import model.Type;

/**
 *
 * @author ducky
 */
public class SettingDetailsController extends HomeDirectorController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String contextPath = request.getContextPath();
        request.setAttribute("contentPageIncluded", "/view/director/settingdetails.jsp");
        //Get setting
        Setting setting = (Setting) request.getSession().getAttribute("setting");
        request.setAttribute("setting", setting);
        
        //Get type list
        TypeConfigController tcc = new TypeConfigController();
        ArrayList<Type> types = tcc.getTypesList();
        for (int i = 0; i < types.size(); i++) {
            if (types.get(i).getName().compareTo(setting.getType()) == 0) {
                types.remove(i);
            }
        }
        request.setAttribute("types", types);
        this.loadHeader(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("settingId");
        String name = request.getParameter("settingName");
        String description = request.getParameter("settingDescription");
        String type = request.getParameter("settingType");
        String status = request.getParameter("settingStatus");
        String value = request.getParameter("settingValue");
        SettingDBContext sdb = new SettingDBContext();
        Setting setting = new Setting(Integer.parseInt(id), name, type, description, value, status);
        boolean isSuccess = sdb.updateSetting(setting);
        if (isSuccess){
            response.getWriter().print("success");
        }else{
            response.getWriter().print("fail");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
