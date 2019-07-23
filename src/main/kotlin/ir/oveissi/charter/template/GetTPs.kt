package ir.oveissi.charter.template

data class GetTPs(val from:String,
                  val to:String,
                  val result: MutableList<TP>)