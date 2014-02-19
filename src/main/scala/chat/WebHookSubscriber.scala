package chat

import akka.actor.Actor
import akka.contrib.pattern.DistributedPubSubExtension
import akka.contrib.pattern.DistributedPubSubMediator.Subscribe
import akka.actor.ActorLogging
import akka.actor.ActorRef
import akka.actor.Props

object WebHookSubscriber{
  
  def props(actorRef:ActorRef):Props = Props(new WebHookSubscriber(actorRef))
}
class WebHookSubscriber(actorRef:ActorRef) extends Actor with ActorLogging{
	
  val mediator = actorRef
  mediator ! Subscribe("channel",self)
  def receive = {
    case Tick1(message:String) => log.error("from subscriber"+message)
    case _ => log.error("some thing else");
  }
}