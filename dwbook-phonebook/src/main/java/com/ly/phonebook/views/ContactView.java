package com.ly.phonebook.views;

import com.ly.representations.Contact;
import io.dropwizard.views.View;

/**
 * All about user interface view
 */
public class ContactView extends View {
    private final Contact contact;

    public ContactView(Contact contact) {
        super("/views/contact.mustache");
        this.contact = contact;
    }

    public Contact getContact() {
        return contact;
    }
}
