import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso

@SpringBootApplication
//@EnableOAuth2Sso
class BootConfig

object DemoApplication {

  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[BootConfig], args: _*)
  }
}
