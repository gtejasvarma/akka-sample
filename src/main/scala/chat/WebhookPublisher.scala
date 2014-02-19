package chat

import akka.actor.Actor
import akka.contrib.pattern.DistributedPubSubExtension
import scala.concurrent.duration._
import akka.actor.ActorLogging
import akka.contrib.pattern.DistributedPubSubMediator.Publish
import akka.actor.Props
import akka.actor.ActorSystem

case class Tick1(message:String)

class WebhookPublisher(actorSystem:ActorSystem) extends Actor with ActorLogging{

  import context._
  
  
  def scheduler = context.system.scheduler

  
    override def preStart(): Unit =
    scheduler.scheduleOnce(5.seconds,self,Tick1("first"))

  val mediator = DistributedPubSubExtension(context.system).mediator
  
  def receive = {
    case Tick1(message:String) =>
      scheduler.scheduleOnce(5.seconds, self, Tick1("non first"))
      log.error("from publisher:"+message)
      mediator ! Publish("channel",message)
    case _ => log.info("unknow message type")
  }
}