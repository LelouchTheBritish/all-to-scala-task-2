package tinkoff.scala.secondtask.domain;

import java.util.List;

public record Event(List<Address> recipients, Payload payload) {
}
