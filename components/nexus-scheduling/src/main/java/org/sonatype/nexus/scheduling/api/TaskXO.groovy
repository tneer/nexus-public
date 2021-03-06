/*
 * Sonatype Nexus (TM) Open Source Version
 * Copyright (c) 2008-present Sonatype, Inc.
 * All rights reserved. Includes the third-party code listed at http://links.sonatype.com/products/nexus/oss/attributions.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License Version 1.0,
 * which accompanies this distribution and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Sonatype Nexus (TM) Professional Version is available from Sonatype, Inc. "Sonatype" and "Sonatype Nexus" are trademarks
 * of Sonatype, Inc. Apache Maven is a trademark of the Apache Software Foundation. M2eclipse is a trademark of the
 * Eclipse Foundation. All other trademarks are the property of their respective owners.
 */
package org.sonatype.nexus.scheduling.api

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import org.sonatype.nexus.scheduling.TaskInfo

/**
 * Task transfer object for REST APIs.
 * 
 * @since 3.6.0
 */
@CompileStatic
@ToString(includePackage = false, includeNames = true)
@EqualsAndHashCode(includes = ['id'])
class TaskXO
{
  String id

  String name

  String message

  String currentState

  String lastRunResult

  Date nextRun

  Date lastRun

  static TaskXO fromTaskInfo(final TaskInfo taskInfo) {
    TaskXO taskXO = new TaskXO()

    taskXO.id = taskInfo.id
    taskXO.name = taskInfo.name
    taskXO.message = taskInfo.message
    taskXO.currentState = taskInfo.currentState.state.toString()
    taskXO.nextRun = taskInfo.currentState.nextRun
    if (taskInfo.lastRunState != null) {
      taskXO.lastRunResult = taskInfo.lastRunState.endState?.toString()
      taskXO.lastRun = taskInfo.lastRunState.runStarted
    }
    return taskXO
  }
}
