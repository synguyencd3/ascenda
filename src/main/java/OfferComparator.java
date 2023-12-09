import java.util.Comparator;

public class OfferComparator implements Comparator<Offer>{
            public int compare(Offer of1, Offer of2) {
                if (of1.getClosetDistance() > of2.getClosetDistance())
                    return 1;
                else if (of1.getClosetDistance() < of2.getClosetDistance())
                    return -1;
                                return 0;
                }
        }