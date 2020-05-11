package com.codecool.webshop;

import com.codecool.thymeleaf.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "cart", urlPatterns = {"/cart"}, loadOnStartup = 2)
public class Cart extends HttpServlet {

    static List<Item> cart = new ArrayList<>();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        context.setVariable("cartProducts", cart);
        context.setVariable("total", getTotal());
        engine.process("cart.html", context, response.getWriter());

    }



    void addToCart(Item item){
        cart.add(item);
    }

    void removeFromCart(Item item){
        cart.remove(item);
    }

    float getTotal(){
        float total = 0;
        for(Item item : cart){
            total += item.getPrice();
        }
        return (float)Math.round(total * 100f) / 100f;
    }


}
