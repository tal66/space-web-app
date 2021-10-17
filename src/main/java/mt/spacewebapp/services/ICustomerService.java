package mt.spacewebapp.services;

import mt.spacewebapp.models.Customer;
import mt.spacewebapp.models.Ticket;

import java.util.List;

public interface ICustomerService {
    public Customer findByUserName(String name);
    public List<Ticket> getUserTickets(String username);
}
