package models

import javax.inject._
import play.api.mvc._
import play.api.db._
import anorm._
import anorm.SqlParser._

@Singleton
class DBAccess @Inject()(db: Database) {
  val parser = str("id") ~ str("name") ~ date("created_at")
  val mapper = parser.map {
    case id ~ name ~ created_at => Map("id" -> id, "name" -> name, "created_at" -> created_at)
  }

  def userList: List[Map[String,Any]] = {
    db.withConnection { implicit c =>
      SQL("SELECT * FROM test_users").as(mapper.*)
    }
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
