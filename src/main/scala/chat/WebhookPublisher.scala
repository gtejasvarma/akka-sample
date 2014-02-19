package chat

import akka.actor.Actor
import akka.contrib.pattern.DistributedPubSubExtension
import scala.concurrent.duration._
import akka.actor.ActorLogging
import akka.contrib.pattern.DistributedPubSubMediator.Publish
import akka.actor.Props
import akka.actor.ActorSystem
import akka.actor.ActorRef

case class Tick1(message:String)

object WebhookPublisher{
  
  def props(actorSystem:ActorRef): Props = Props(new WebhookPublisher(actorSystem))
  
}
class WebhookPublisher(actorRef:ActorRef) extends Actor with ActorLogging{

  import context._
  
  
  def scheduler = context.system.scheduler

  
    override def preStart(): Unit =
    scheduler.scheduleOnce(5.seconds,self,Tick1("first"))

  val mediator = actorRef
  
  def receive = {
    case Tick1(message:String) =>
      scheduler.scheduleOnce(5.seconds, self, Tick1("non first"))
      log.error("from publisher:"+message)
      mediator ! Publish("channel",Tick1(message))
    case _ => log.info("unknow message type")
  }
}