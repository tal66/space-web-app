package mt.spacewebapp.services;

import mt.spacewebapp.data.CustomerRepository;
import mt.spacewebapp.models.Customer;
import mt.spacewebapp.models.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findByUserName(String name){
        return customerRepository.findByUserName(name);
    }

    public List<Ticket> getUserTickets(String username) {
        Customer customer = customerRepository.findByUserName(username);
        return customer.getTickets();
    }
}
