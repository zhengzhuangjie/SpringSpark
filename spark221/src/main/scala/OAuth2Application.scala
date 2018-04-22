import org.springframework.boot.SpringApplication

/**
  * https://spring.io/guides/tutorials/spring-boot-oauth2/
  */
object OAuth2Application {

  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[BootConfig], args: _*)
  }
}
