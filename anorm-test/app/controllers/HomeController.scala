package controllers

import akka.util.ByteString
import javax.inject._
import models.DBAccess
import play.api._
import play.api.http.HttpEntity
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._



/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(db: DBAccess, cc: ControllerComponents) extends AbstractController (cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def userList = Action {
    val users = db.userList
    Ok(Json.prettyPrint(users))
//    Result(
//      header = ResponseHeader(200, Map.empty),
//      body = HttpEntity.Strict(Json(users), Some("application/json"))
//    )
  }

  def insert =  Action{
    db.insert
    Redirect("/user_list")
  }
}
