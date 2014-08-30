package models

import java.awt.event.KeyEvent._

object Command{
  private val keys: Map[String, Int] = Map(
    //Numbers
    "0" -> VK_0, "1" -> VK_1, "2" -> VK_2, "3" -> VK_3, "4" -> VK_4, "5" -> VK_5, "6" -> VK_6, "7" -> VK_7,
    "8" -> VK_8, "9" -> VK_9,

    //Words
    "a" -> VK_A, "b" -> VK_B, "c" -> VK_C, "d" -> VK_D, "e" -> VK_E, "f" -> VK_F, "g" -> VK_G, "h" -> VK_H,
    "i" -> VK_I, "j" -> VK_J, "k" -> VK_K, "l" -> VK_L, "m" -> VK_M, "n" -> VK_N, "o" -> VK_O, "p" -> VK_P,
    "q" -> VK_Q, "r" -> VK_R, "s" -> VK_S, "t" -> VK_T, "u" -> VK_U, "v" -> VK_V, "w" -> VK_W, "x" -> VK_X,
    "y" -> VK_Y,
    "z" -> VK_Z,

    //signs
    "=" -> VK_EQUALS, "_" -> VK_UNDERSCORE, "{" -> VK_BRACELEFT, "}" -> VK_BRACERIGHT, "*" -> VK_ASTERISK,
    "!" -> VK_EXCLAMATION_MARK, "?" -> VK_INVERTED_EXCLAMATION_MARK, "-" -> VK_SUBTRACT,

    //Command keys
    "enter" -> VK_ENTER, "esc" -> VK_ESCAPE, "shift" -> VK_SHIFT, "ctrl" -> VK_CONTROL, "tab" -> VK_TAB,
    "alt" -> VK_ALT, "del" -> VK_DELETE, "insert" -> VK_INSERT, "super" -> VK_WINDOWS, "backspace" -> VK_BACK_SPACE,
    "capslock" -> VK_CAPS_LOCK, "space" -> VK_SPACE,

    //Arrows
    "arrow_up" -> VK_UP, "arrow_down" -> VK_DOWN, "arrow_left" -> VK_LEFT, "arrow_right" -> VK_RIGHT,

    //F's
    "f1" -> VK_F1, "f2" -> VK_F2,   "f3" -> VK_F3,   "f4" -> VK_F4, "f5" -> VK_F5, "f6" -> VK_F6,
    "f7" -> VK_F7,   "f8" -> VK_F8, "f9" -> VK_F9, "f10" -> VK_F10, "f11" -> VK_F11, "f12" -> VK_F12
  )

  def getKey(s: String): Option[Int] = keys get s
}