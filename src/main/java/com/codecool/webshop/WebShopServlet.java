package com.codecool.webshop;

import com.codecool.thymeleaf.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@WebServlet(name = "webShop", urlPatterns = {"/"}, loadOnStartup = 1)
public class WebShopServlet extends HttpServlet {

    Stock stocks = new Stock();
    Cart cart = new Cart();

    @Override
    public void init() {
        stocks.stockSet.add(new Item(1, "USB Wall Charger", 21.99f));
        stocks.stockSet.add(new Item(2, "Ameritex Anti-Slip Loveseat Cover", 33.99f));
        stocks.stockSet.add(new Item(3, "MorePro Fitness Tracker", 30.51f));
        stocks.stockSet.add(new Item(4, "Airtight Coffee Canister", 16.99f));

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        int countProducts = 0;
        context.setVariable("count", countProducts);
        context.setVariable("products", stocks.stockSet);
        engine.process("index.html", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {


        String addToCartId = request.getParameter("add");
        String removeFromCartId = request.getParameter("remove");

        if (addToCartId != null) {
            Item item = stocks.getProductById(Integer.parseInt(addToCartId));
            cart.addToCart(item);
        } else if (removeFromCartId != null) {
            Item item = stocks.getProductById(Integer.parseInt(removeFromCartId));
            cart.removeFromCart(item);
        }

        response.sendRedirect(request.getHeader("referer"));
    }
}
