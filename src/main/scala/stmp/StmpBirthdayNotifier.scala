package stmp

import core.BirthdayNotifier
import core.BirthdayNotifier.BirthdayMessage
import javax.mail.Message
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class StmpBirthdayNotifier(host: String, port: Int) extends BirthdayNotifier {

  def sendMessage(message: BirthdayMessage) {
    val session = createSession
    val mimeMessage = buildMessage(session, message)
    sendMessage(mimeMessage)
  }

  private def sendMessage(message: MimeMessage) {
    Transport.send(message)
  }

  private def createSession: Session = {
    val properties = new java.util.Properties()
    properties.put("mail.smtp.host", host)
    properties.put("mail.smtp.port", port.toString)
    Session.getInstance(properties, null)
  }

  private def buildMessage(session: Session, message: BirthdayMessage): MimeMessage = {
    new MimeMessage(session) {
      setFrom(new InternetAddress(message.envelopeSender))
      setRecipient(Message.RecipientType.TO, new InternetAddress(message.envelopeReceiver))
      setSubject(message.subject)
      setText(message.body)
    }
  }
}
