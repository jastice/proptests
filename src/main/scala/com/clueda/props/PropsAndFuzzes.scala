/*
 * Copyright (C) 2014 Clueda AG.
 * This work is proprietary and confidential. Any distribution,
 * reproduction, or modification is strictly prohibited under any
 * circumstances without the express prior written permission of Clueda
 * AG. All rights reserved.
 */
package com.clueda.props

import org.rogach.scallop._

/** The main entry point for Props and Fuzzes. */
object PropsAndFuzzes extends App {

  object Conf extends ScallopConf(args) {
    val param = opt[String]("param", default=Some("default-value"), descr="This is a command line parameter, parsed by Scallop.")
  }

  println("Props and Fuzzes is ready to hack!")
}
