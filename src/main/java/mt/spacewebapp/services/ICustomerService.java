package mt.spacewebapp.services;

import mt.spacewebapp.models.Customer;
import mt.spacewebapp.models.Ticket;

import java.util.List;

public interface ICustomerService {
    Customer findByUserName(String name);

    List<Ticket> getUserTickets(String username);
}
