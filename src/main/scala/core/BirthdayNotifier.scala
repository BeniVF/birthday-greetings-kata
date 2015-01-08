package core

object BirthdayNotifier {
  case class BirthdayMessage(envelopeSender: String, envelopeReceiver: String, subject: String, body: String)
}

trait BirthdayNotifier {
  import BirthdayNotifier._
  def sendMessage(message: BirthdayMessage)
}