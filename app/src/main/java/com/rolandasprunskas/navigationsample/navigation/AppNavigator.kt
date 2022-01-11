package com.rolandasprunskas.navigationsample.navigation

import java.util.TreeSet

object AppNavigator {

    private var states: TreeSet<NavigationState> = sortedSetOf()
    private val backStack = ArrayDeque<NavigationState>(listOf())

    fun init(states: TreeSet<NavigationState>) {
        this.states = states
    }

    /**
     * Finds start screen and adds it to the backstack
     */
    fun findStart(): Screen {
        return findNextState(null).also {
            if (backStack.isEmpty()) {
                backStack.push(it)
            }
        }
    }

    /**
     * Finds and returns next screen and push it to the backstack
     */
    fun goNext(): Screen {
        return findNextState(backStack.lastOrNull()).also {
            backStack.push(it)
        }
    }

    /**
     * Pops current screen from backstack, and returns last one
     */
    fun goBack(): Screen? {
        backStack.pop()
        return backStack.lastOrNull()
    }

    /**
     * Recursive method which iterates navigation states in order to find next one.
     * If null provided for current state, first state which meets shouldShow() condition would be returned.
     * If current state provided, then next state would be returned which meets shouldShow() condition.
     * @param currentState is used to find further state which goes after current.
     * @return found next navigation state
     * @throws IllegalArgumentException if current or next states not found in queue
     */
    private fun findNextState(currentState: NavigationState?): NavigationState {
        val nextState = if (currentState != null) {
            if (states.contains(currentState)) {
                states.higher(currentState)
            } else {
                throw IllegalArgumentException("current state not found in queue")
            }
        } else {
            states.first()
        }

        if (nextState == null) {
            throw IllegalArgumentException("next state not found in queue")
        }

        return if (nextState.shouldShow()) {
            nextState
        } else {
            findNextState(nextState)
        }
    }

    private fun <T> ArrayDeque<T>.push(element: T) = addLast(element)

    private fun <T> ArrayDeque<T>.pop() = removeLastOrNull()
}
