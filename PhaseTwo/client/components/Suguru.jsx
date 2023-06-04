import React, { useEffect, useState } from 'react'
import SuguruRow from './SuguruRow'

export function getName(position) {
    if (position.upper === '0') {
        if (position.lower === '0') return 'zeroZero'
        if (position.lower === '1') return 'zeroOne'
        if (position.lower === '2') return 'zeroTwo'
        if (position.lower === '3') return 'zeroThree'
        if (position.lower === '4') return 'zeroFour'
        if (position.lower === '5') return 'zeroFive'
    }
    if (position.upper === '1') {
        if (position.lower === '0') return 'oneZero'
        if (position.lower === '1') return 'oneOne'
        if (position.lower === '2') return 'oneTwo'
        if (position.lower === '3') return 'oneThree'
        if (position.lower === '4') return 'oneFour'
        if (position.lower === '5') return 'oneFive'
    }
    if (position.upper === '2') {
        if (position.lower === '0') return 'twoZero'
        if (position.lower === '1') return 'twoOne'
        if (position.lower === '2') return 'twoTwo'
        if (position.lower === '3') return 'twoThree'
        if (position.lower === '4') return 'twoFour'
        if (position.lower === '5') return 'twoFive'
    }
    if (position.upper === '3') {
        if (position.lower === '0') return 'threeZero'
        if (position.lower === '1') return 'threeOne'
        if (position.lower === '2') return 'threeTwo'
        if (position.lower === '3') return 'threeThree'
        if (position.lower === '4') return 'threeFour'
        if (position.lower === '5') return 'threeFive'
    }
    if (position.upper === '4') {
        if (position.lower === '0') return 'fourZero'
        if (position.lower === '1') return 'fourOne'
        if (position.lower === '2') return 'fourTwo'
        if (position.lower === '3') return 'fourThree'
        if (position.lower === '4') return 'fourFour'
        if (position.lower === '5') return 'fourFive'
    }
    if (position.upper === '5') {
        if (position.lower === '0') return 'fiveZero'
        if (position.lower === '1') return 'fiveOne'
        if (position.lower === '2') return 'fiveTwo'
        if (position.lower === '3') return 'fiveThree'
        if (position.lower === '4') return 'fiveFour'
        if (position.lower === '5') return 'fiveFive'
    }
    return ''
}

export default function Suguru({ puzzle, puzzleState, setPuzzleState, handleChange }) {

    useEffect(() => {
        setPuzzleState(intiPuzzleState(puzzle))
    }, [puzzle])

    function intiPuzzleState(puzzle) {
        let initPuzzleState = {}
        let rows = puzzle.split('\n')
        for (let i = 0; i < rows.length; i++) {
            let row = rows[i].split(' ')
            for (let j = 0; j < row.length; j++) {
                if (row[j].match(/[0-9]/)) {
                    initPuzzleState[getName({ upper: i.toString(), lower: j.toString() })] = row[j].match(/[0-9]/)[0]
                }
            }
        }
        return initPuzzleState;
    }

    return (
        <div>
            <table>
                <tbody>
                    {
                        puzzle?.split('\n').map((row, index) => {
                            return <tr key={`suguru-row-${index}`} >
                                <SuguruRow puzzleState={puzzleState} row={row} handleChange={handleChange} upperIndex={index} />
                            </tr>
                        })
                    }
                </tbody>
            </table>
        </div>
    )
}
