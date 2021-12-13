package mt.spacewebapp.services.impl;

import mt.spacewebapp.data.CustomerRepository;
import mt.spacewebapp.models.Customer;
import mt.spacewebapp.models.Ticket;
import mt.spacewebapp.models.enums.TicketStatus;
import mt.spacewebapp.services.ICustomerService;
import mt.spacewebapp.services.ITicketService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerService {
    private CustomerRepository customerRepository;
    private ITicketService ticketService;
    private Map<String, Customer> usernameToCustomer;

    public CustomerService(CustomerRepository customerRepository, ITicketService ticketService) {
        this.customerRepository = customerRepository;
        this.ticketService = ticketService;
        usernameToCustomer = new HashMap<>();
    }

    @Override
    public Customer findByUserName(String username){
        return mapGet(username);
    }

    @Override
    public List<Ticket> getUserTickets(String username) {
        Customer customer = mapGet(username);
        return ticketService.findByCustomer(customer).stream()
                .filter(ticket -> ticket.getStatus() == TicketStatus.VALID)
                .collect(Collectors.toList());
    }

    private Customer mapGet(String name) {
        if (!usernameToCustomer.containsKey(name)){
            Customer customer = customerRepository.findByUserName(name);
            usernameToCustomer.put(name, customer);
        }
        return usernameToCustomer.get(name);
    }

}
