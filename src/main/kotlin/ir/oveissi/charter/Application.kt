package ir.oveissi.charter

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@EnableAutoConfiguration
@Configuration
class Application {
    @Bean
    fun controller() = TicketPriceController()
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}