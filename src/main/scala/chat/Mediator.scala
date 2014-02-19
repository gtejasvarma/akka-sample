package chat

import akka.contrib.pattern.DistributedPubSubExtension
import akka.actor.ActorSystem
import akka.actor.ActorRef

object Mediator {

  var mediator1 = null;
  
  def init(actorSystem:ActorSystem):ActorRef={
      DistributedPubSubExtension(actorSystem).mediator
     mediator1
  }
}