<%@ page isErrorPage="true" import="java.io.PrintWriter" %>

<html><body>
        <style>body{background:#aacded;}</style>
        <h1 style="color: white">SeaDataNet Loading page Error</h1>
        <div style="border:2px blue ridge;background:LightCyan;">
            <pre>
  <%
            // unwrap ServletExceptions.
            while (exception instanceof ServletException) {
                exception = ((ServletException) exception).getRootCause();            // print stack trace.
            }
            exception.printStackTrace(new PrintWriter(out));
  %>
            </pre>
        </div>
</body></html>
