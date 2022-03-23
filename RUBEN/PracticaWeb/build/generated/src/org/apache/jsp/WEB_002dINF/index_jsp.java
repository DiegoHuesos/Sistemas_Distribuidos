package org.apache.jsp.WEB_002dINF;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Sistema de cotizacion de seguro de autos</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1>Sistema de cotizacion de seguro de autos</h1>        \n");
      out.write("        <form action=\"auto.jsp\">\n");
      out.write("                                   \n");
      out.write("            <table border=\"1\">\n");
      out.write("                <thead>\n");
      out.write("                    <tr>\n");
      out.write("                        <th>Campo</th>\n");
      out.write("                        <th>Valor</th>\n");
      out.write("                    </tr>\n");
      out.write("                </thead>\n");
      out.write("                <tbody>\n");
      out.write("                    <tr>\n");
      out.write("                        <td>Nombre</td>\n");
      out.write("                        <td><input type=\"text\" name=\"Nombre\" value=\"\" /></td>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr>\n");
      out.write("                        <td>Apellido </td>\n");
      out.write("                        <td><input type=\"text\" name=\"Apellido\" value=\"\" /></td>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr>\n");
      out.write("                        <td>Genero</td>\n");
      out.write("                        <td>\n");
      out.write("                            <input type=\"radio\" name=\"Genero\" value=\"Hombre\" />Hombre\n");
      out.write("                            <input type=\"radio\" name=\"Genero\" value=\"Mujer\" />Mujer\n");
      out.write("                        </td>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr>\n");
      out.write("                        <td>Edad</td>\n");
      out.write("                        <td><input type=\"text\" name=\"Edad\" value=\"\" /></td>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr>\n");
      out.write("                        <td>Estado</td>\n");
      out.write("                        <td><select name=\"Estado\">\n");
      out.write("                                <option>Jalisco</option>\n");
      out.write("                                <option>CDMX</option>\n");
      out.write("                                <option>Nuevo Leon</option>\n");
      out.write("                                <option>Yucatan</option>\n");
      out.write("                            </select></td>\n");
      out.write("                    </tr>\n");
      out.write("                                        \n");
      out.write("                   <tr>\n");
      out.write("                        <td><input type=\"reset\" value=\"Limpiar\" name=\"Limpiar\" /></td>\n");
      out.write("                        <td><input type=\"submit\" value=\"Enviar\" name=\"Enviar\" /></td>\n");
      out.write("                    </tr>\n");
      out.write("                    \n");
      out.write("                </tbody>\n");
      out.write("            </table>\n");
      out.write("\n");
      out.write("\n");
      out.write("        </form>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
