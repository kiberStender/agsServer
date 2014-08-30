package models

import java.awt.event.KeyEvent

object Command{
  private val keys: Map[String, Int] = Map(
    //Numbers
    "0" -> KeyEvent.VK_0, "1" -> KeyEvent.VK_1, "2" -> KeyEvent.VK_2, "3" -> KeyEvent.VK_3, "4" -> KeyEvent.VK_4,
    "5" -> KeyEvent.VK_5, "6" -> KeyEvent.VK_6, "7" -> KeyEvent.VK_7, "8" -> KeyEvent.VK_8, "9" -> KeyEvent.VK_9,

    //Words
    "a" -> KeyEvent.VK_A, "b" -> KeyEvent.VK_B, "c" -> KeyEvent.VK_C, "d" -> KeyEvent.VK_D, "e" -> KeyEvent.VK_E,
    "f" -> KeyEvent.VK_F, "g" -> KeyEvent.VK_G, "h" -> KeyEvent.VK_H, "i" -> KeyEvent.VK_I, "j" -> KeyEvent.VK_J,
    "k" -> KeyEvent.VK_K, "l" -> KeyEvent.VK_L, "m" -> KeyEvent.VK_M, "n" -> KeyEvent.VK_N, "o" -> KeyEvent.VK_O,
    "p" -> KeyEvent.VK_P, "q" -> KeyEvent.VK_Q, "r" -> KeyEvent.VK_R, "s" -> KeyEvent.VK_S, "t" -> KeyEvent.VK_T,
    "u" -> KeyEvent.VK_U, "v" -> KeyEvent.VK_V, "w" -> KeyEvent.VK_W, "x" -> KeyEvent.VK_X, "y" -> KeyEvent.VK_Y,
    "z" -> KeyEvent.VK_Z,

    //signs
    "=" -> KeyEvent.VK_EQUALS, "_" -> KeyEvent.VK_UNDERSCORE, "{" -> KeyEvent.VK_BRACELEFT, "}" -> KeyEvent.VK_BRACERIGHT,
    "*" -> KeyEvent.VK_ASTERISK, "!" -> KeyEvent.VK_EXCLAMATION_MARK, "?" -> KeyEvent.VK_INVERTED_EXCLAMATION_MARK,
    "-" -> KeyEvent.VK_SUBTRACT,

    //Command keys
    "enter" -> KeyEvent.VK_ENTER, "esc" -> KeyEvent.VK_ESCAPE, "shift" -> KeyEvent.VK_SHIFT, "ctrl" -> KeyEvent.VK_CONTROL,
    "tab" -> KeyEvent.VK_TAB, "alt" -> KeyEvent.VK_ALT, "del" -> KeyEvent.VK_DELETE, "insert" -> KeyEvent.VK_INSERT,
    "super" -> KeyEvent.VK_WINDOWS, "backspace" -> KeyEvent.VK_BACK_SPACE, "capslock" -> KeyEvent.VK_CAPS_LOCK,
    "space" -> KeyEvent.VK_SPACE,

    //Arrows
    "arrow_up" -> KeyEvent.VK_UP, "arrow_down" -> KeyEvent.VK_DOWN,
    "arrow_left" -> KeyEvent.VK_LEFT, "arrow_right" -> KeyEvent.VK_RIGHT,

    //F's
    "f1" -> KeyEvent.VK_F1, "f2" -> KeyEvent.VK_F2,   "f3" -> KeyEvent.VK_F3,   "f4" -> KeyEvent.VK_F4,
    "f5" -> KeyEvent.VK_F5, "f6" -> KeyEvent.VK_F6,   "f7" -> KeyEvent.VK_F7,   "f8" -> KeyEvent.VK_F8,
    "f9" -> KeyEvent.VK_F9, "f10" -> KeyEvent.VK_F10, "f11" -> KeyEvent.VK_F11, "f12" -> KeyEvent.VK_F12
  )

  def getKey(s: String): Option[Int] = keys get s
}