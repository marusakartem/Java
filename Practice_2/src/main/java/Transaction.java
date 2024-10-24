import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Transaction {
    private String date;
    private double amount;
    private String description;
}
