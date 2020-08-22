package com.example.demo.rest;

import com.example.demo.models.util.ShoppingCart;
import com.example.demo.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InvoiceRestController {

    @Autowired
    private InvoiceService invoiceService;

    @RequestMapping(method = RequestMethod.POST, path = "/invoice")
    public List<ShoppingCart> invoice(@RequestBody List<ShoppingCart> shoppingCarts) {
        return invoiceService.invoice(shoppingCarts);
    }
}
