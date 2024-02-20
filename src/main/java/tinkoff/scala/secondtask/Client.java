package tinkoff.scala.secondtask;

import tinkoff.scala.secondtask.domain.Address;
import tinkoff.scala.secondtask.domain.Event;
import tinkoff.scala.secondtask.domain.Payload;
import tinkoff.scala.secondtask.domain.Result;

public interface Client {
    //блокирующий метод для чтения данных
    Event readData();

    //блокирующий метод отправки данных
    Result sendData(Address dest, Payload payload);
}
