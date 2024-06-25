package astrelya.test.kafka

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.TopicPartitionOffset
import org.springframework.stereotype.Service


@Service
class MessageService {
    @Autowired
    private lateinit var kafkaTemplate: KafkaTemplate<String, String>


    fun sendMessage(msg: String) {
        kafkaTemplate.send("astrelyaTopic", msg)
    }

    fun receiveMessage(offset:Long):  String? {
        val consumerRecord = kafkaTemplate.receive("astrelyaTopic", 0, offset)
        println(consumerRecord?.key())
        println(consumerRecord?.value())
        return consumerRecord?.value()
    }

    fun receiveMessages(): String? {
        val consumerRecords = kafkaTemplate.receive(listOf(TopicPartitionOffset("astrelyaTopic", 0)))
        return consumerRecords.joinToString("\n") { it.value() }
    }

}