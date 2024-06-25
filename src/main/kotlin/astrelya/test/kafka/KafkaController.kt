package astrelya.test.kafka

import jakarta.websocket.server.PathParam
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/kafka")
class KafkaController {

    @Autowired
    private lateinit var messageService: MessageService

    @PostMapping(path = ["/message"])
    public fun produceMessage(@PathParam("msg") msg: String){
        messageService.sendMessage(msg)
    }

    @GetMapping("/message/{offset}")
    public fun consumeMessage(@PathVariable offset: Long): String? {
        return messageService.receiveMessage(offset)
    }
}