package us.bleibinha.spray.json.macros

import org.specs2.mutable.Specification
import spray.json._
import DefaultJsonProtocol._

@json case class City(name: String)
@json case class Person(name: String, age: Int)

@jsonstrict case class City2(name: String)
@jsonstrict case class Person2(name: String, age: Int)

class JsonFormatAnnotationTest extends Specification {

  "@json annotation" should {

    "create correct formatter for case class with 1 field" in {

      val city = City("San Francisco")
      val json = city.toJson
      json === JsString("San Francisco")
      Option(json.convertTo[City]) must beSome(city)
    }

    "create correct formatter for case class with >= 2 fields" in {

      val person = Person("Victor Hugo", 46)
      val json = person.toJson
      json === JsObject(
        "name" -> JsString("Victor Hugo"),
        "age" -> JsNumber(46)
      )
      Option(json.convertTo[Person]) must beSome(person)
    }
  }

  "@jsonstrict annotation" should {

    "create correct formatter for case class with 1 field" in {

      val city = City2("San Francisco")
      val json = city.toJson
      json === JsObject("name" -> JsString("San Francisco"))
      Option(json.convertTo[City2]) must beSome(city)
    }

    "create correct formatter for case class with >= 2 fields" in {

      val person = Person2("Victor Hugo", 46)
      val json = person.toJson
      json === JsObject(
        "name" -> JsString("Victor Hugo"),
        "age" -> JsNumber(46)
      )
      Option(json.convertTo[Person2]) must beSome(person)
    }
  }
}
