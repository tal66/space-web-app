package mt.spacewebapp.data.Specs;

import mt.spacewebapp.models.Ticket;
import mt.spacewebapp.models.Ticket_;
import mt.spacewebapp.models.enums.TicketStatus;
import org.springframework.data.jpa.domain.Specification;

public class TicketSpecs {
    public static Specification<Ticket> isValid(){
        return (root, query, builder) -> {
            return builder.equal(root.get(Ticket_.status), TicketStatus.VALID);
        };
    }

    public static Specification<Ticket> whereStatus(TicketStatus status){
        return (root, query, builder) -> {
            return builder.equal(root.get(Ticket_.status), status);
        };
    }

    public static Specification<Ticket> whereTripId(Integer id){
        return (root, query, builder) -> {
            return builder.equal(root.get(Ticket_.trip), id);
        };
    }

}
