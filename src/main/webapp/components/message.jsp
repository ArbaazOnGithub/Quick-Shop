 
<%
     String message = (String)session.getAttribute("message");
     if(message!=null)
     {
         //out.println(message);
 %>
 <div class="alert alert-success alert-dismissible fade show" role="alert">
  <strong><%= message %></strong>

</div>
         
 <%     session.removeAttribute("message");
     }
     

%>
