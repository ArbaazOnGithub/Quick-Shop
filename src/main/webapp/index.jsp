<%@page import="com.mycart.helper.Helper"%>
<%@page import="com.mycart.entities.Category"%>
<%@page import="com.mycart.Dao.CategoryDao"%>
<%@page import="com.mycart.entities.Product"%>
<%@page import="java.util.*"%>
<%@page import="com.mycart.Dao.ProductDao"%>
<%@page import="com.mycart.helper.FactoryProvider"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Cart</title>
        <%@ include file="components/common_css_js.jsp" %> 
    </head>
    <body>
        <%@ include file="components/navbar.jsp" %>
        <div class="container-fluid">
            <div class="row mt-3 mx-2">
                <%
                try {
                    String cat = request.getParameter("category");
                    ProductDao dao = new ProductDao(FactoryProvider.getFactory());
                    List<Product> list = null;
                    
                    if (cat == null || cat.trim().equals("all")) {
                        list = dao.getAllProducts();
                    } else {
                        int cid = Integer.parseInt(cat.trim());
                        list = dao.getAllProductsById(cid);
                    }

                    CategoryDao cdao = new CategoryDao(FactoryProvider.getFactory());
                    List<Category> clist = cdao.getCategories();
                %>
                <!-- show category-->
                <div class="emp mt-5"></div>
                <div class="col-md-2">
                    <div class="list-group mt-4">
                        <a href="index.jsp?category=all" class="list-group-item list-group-item-action btn-success <%=cat == null || cat.trim().equals("all") ? "selected-category" : "" %>">
                            All Products
                        </a>
                        <% for (Category c : clist) { %>
                        <a href="index.jsp?category=<%=c.getCategoryId()%>" class="list-group-item list-group-item-action btn-success <%=cat != null && cat.trim().equals(String.valueOf(c.getCategoryId())) ? "selected-category" : "" %>">
                            <%=c.getCategoryTitle()%>
                        </a>
                        <% } %>
                    </div>
                </div>
                <!-- show product-->
                <div class="col-md-10">
                    <div class="row row-cols-1 row-cols-md-3 g-4 mt-0">
                        <% for (Product p : list) { %>
                        <div class="col">
                            <div class="card">
                                <div class="container text-center">
                                    <img src="img/products/<%=p.getpPhoto()%>" style="max-height: 200px; max-width: 100%; width: auto;" class="card-img-top m-2" alt="...">
                                </div>
                                <div class="card-body">
                                    <h5 class="card-title"><%=Helper.get10Words(p.getpName())%></h5>
                                    <p class="card-text">
                                        <%=Helper.get10Words(p.getpDesc())%>
                                    </p>
                                </div>
                                <div class="card-footer text-center">
                                    <button class="btn custom-bg text-white" onclick="add_to_cart(<%=p.getPId()%>, '<%=p.getpName()%>', <%=p.getPriceAfterApplyingDiscount()%>)">Add to cart</button>
                                    <button class="btn btn-outline-success"> &#8377;<%=p.getPriceAfterApplyingDiscount()%>/- <span class="text-secondary discount-label">&#8377;<%=p.getpPrice()%> <%=p.getpDiscount()%>% off</span></button>
                                </div>
                            </div>
                        </div>
                        <% } %>

                        <% if (list.size() == 0) { %>
                        <div class="col-md-12">
                            <h3 class="text-center">No items in this category</h3>
                        </div>
                        <% } %>
                    </div>
                </div>
                <% } catch (Exception e) {
                    e.printStackTrace();
                    out.println("<div class='alert alert-danger'>Error fetching products.</div>");
                } %>
            </div>
        </div>
        <%@ include file="components/common_modals.jsp" %>
    </body>
</html>
