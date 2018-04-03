/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  Platform,
  StyleSheet,
  Text,
  View
} from 'react-native';
import Kanban from './KanbanBridgeNativeView'

const instructions = Platform.select({
  ios: 'Press Cmd+R to reload,\n' +
    'Cmd+D or shake for dev menu',
  android: 'Double tap R on your keyboard to reload,\n' +
    'Shake or press menu button for dev menu',
});

type Props = {};
export default class App extends Component<Props> {
  render() {
    return (
      <Kanban
        style={styles.kanban}
        onStartDragging={e => console.log(e.nativeEvent)}
        onEndDragging={e => console.log(e.nativeEvent)}
        dataSource={[
          {header: 'Column 1', items: [{id: 1, text: 'text 1'}]},
          {header: 'Column 2', items: [
            {id: 2, text: 'text 2'},
            {id: 3, text: 'text 3'}
          ]},
          {header: 'Column 3', items: []}
        ]}
      />
    );
  }
}

const styles = StyleSheet.create({
  kanban: {
    flex: 1,
  },
});
