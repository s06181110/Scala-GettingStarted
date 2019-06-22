package models

import java.util.Date

import javax.inject._
import play.api.libs.json.{JsValue, Json, Reads, Writes}
import play.api.mvc._
import play.api.db._
import anorm._
import anorm.SqlParser._

case class User(id: String, name: String, created_at: Date)
object User {
  implicit val jsonWrites: Writes[User] = Json.writes[User]
  implicit val jsonReads: Reads[User] = Json.reads[User]
}


case class Response(users: JsValue)
object Response {
  implicit val jsonWrites: Writes[Response] = Json.writes[Response]
}

@Singleton
class DBAccess @Inject()(db: Database) {
  val parser = str("id") ~ str("name") ~ date("created_at")
  val mapper = parser.map{ case id~name~created_at => User(id,name,created_at) }


  def getUsers: List[User] = {
    db.withConnection { implicit c =>
      SQL("SELECT * FROM test_users").as(mapper.*)
    }
  }

  def userList: JsValue = {
    Json.toJson(Response(Json.toJson(getUsers)))
  }

  def insert: Option[Long] = {
    db.withConnection { implicit c =>
      SQL(
        """
          |insert into test_users(id, name)
          |values('11111111-1111-1111-1111-111111111111' , 'hoge')
        """.stripMargin).executeInsert()
    }
  }
}
