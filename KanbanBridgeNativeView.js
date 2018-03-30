//  Created by react-native-create-bridge

import React, { Component } from 'react'
import PropTypes from 'proptypes'
import { requireNativeComponent } from 'react-native'

const KanbanBridge = requireNativeComponent('KanbanBridge', KanbanBridgeView)

export default class KanbanBridgeView extends Component {
  render () {
    return <KanbanBridge {...this.props} />
  }
}

