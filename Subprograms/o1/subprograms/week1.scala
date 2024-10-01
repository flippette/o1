package o1.subprograms

import o1.*
import scala.math.*
// WRITE YOUR OWN FUNCTIONS HERE, BELOW THE IMPORT COMMAND:

def toMeters(feet: Double, inches: Double) = (feet * 12 + inches) * 2.54 / 100
def volumeOfCube(edge: Double) = pow(edge, 3)
def areaOfCube(edge: Double) = pow(edge, 2) * 6
def row(cell: Int) = cell / 8
def column(cell: Int) = cell % 8
def accompany(msg: String, mel: String) =
  println(msg)
  play(mel)
def verticalBar(width: Int) = rectangle(width, width * 10, Blue)
def verticalBar(width: Int, color: Color) = rectangle(width, width * 10, color)
def overallGrade(project: Int, examBonus: Int, participationBonus: Int) = min(5, project + examBonus + participationBonus)

def leaguePoints(wins: Int, draws: Int) = wins * 3 + draws
def teamStats(name: String, wins: Int, draws: Int, losses: Int) =
  val total = wins + draws + losses
  s"$name: $wins/$total wins, $draws/$total draws, $losses/$total losses, ${leaguePoints(wins, draws)} points"

def toInches(meters: Double) = meters * 100 / 2.54
def wholeFeet(meters: Double) = (toInches(meters) - remainingInches(meters)) / 12
def remainingInches(meters: Double) = toInches(meters) % 12
def toFeetAndInches(meters: Double) = (wholeFeet(meters), remainingInches(meters))

def verbalEvaluation(projectGrade: Int, examBonus: Int, participationBonus: Int) =
  val descriptions = Buffer("failed", "acceptable", "satisfactory", "good", "very good", "excellent")
  descriptions(overallGrade(projectGrade, examBonus, participationBonus))

def doubleScore(scores: Buffer[Int], id: Int) = scores(id - 1) *= 2

def penalize(scores: Buffer[Int], id: Int, penalty: Int) =
  val score = max(1, scores(id - 1) - penalty)
  val actualPenalty = scores(id - 1) - score
  scores(id - 1) = score
  actualPenalty

// Here is a buggy piece of code for you to fix in one of Chapter 1.7’s assignments.
def onTwoInstruments(melody: String, first: Int, second: Int, lengthOfPause: Int) =
  val melodyUsingFirst  = "[" + first  + "]" + melody
  val melodyUsingSecond = "[" + second + "]" + melody
  val pause = " " * lengthOfPause
  val playedTwice = melodyUsingFirst + pause + melodyUsingSecond