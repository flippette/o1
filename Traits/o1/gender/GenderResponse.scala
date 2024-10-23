package o1.gender

sealed trait GenderResponse
sealed trait Selected(val chosenLabel: String) extends GenderResponse

object Male extends Selected("male")
object Female extends Selected("female")
object NonBinary extends Selected("non-binary")

class Specified(val description: String) extends GenderResponse

object PreferNotToSay extends GenderResponse


@main def test() =
  for response <- Vector(NonBinary, Male, PreferNotToSay, Female, Specified("agender")) do
    response match
      case selectedGender: Selected =>
        println(s"selected option: ${selectedGender.chosenLabel}")
      case specifiedGender: Specified =>
        println(s"self-described: ${specifiedGender.description}")
  end for
