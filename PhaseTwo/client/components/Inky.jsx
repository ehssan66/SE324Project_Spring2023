import React, { useEffect, useState } from 'react'
import InkyRow from './InkyRow'

export function getName(position) {
    if (position.upper === '0' && position.lower === '0') return 'topLeft'
    if (position.upper === '0' && position.lower === '1') return 'top'
    if (position.upper === '0' && position.lower === '2') return 'topRight'
    if (position.upper === '1' && position.lower === '0') return 'left'
    if (position.upper === '1' && position.lower === '1') return 'center'
    if (position.upper === '1' && position.lower === '2') return 'right'
    if (position.upper === '2' && position.lower === '0') return 'bottomLeft'
    if (position.upper === '2' && position.lower === '1') return 'bottom'
    if (position.upper === '2' && position.lower === '2') return 'bottomRight'
    return ''
}

export default function Inky({ puzzle, puzzleState, handleChange }) {


    // const [puzzleState, setPuzzleState] = useState({
    //     topLeft: '',
    //     top: '',
    //     topRight: '',
    //     left: '',
    //     center: '',
    //     right: '',
    //     bottomLeft: '',
    //     bottom: '',
    //     bottomRight: ''
    // })

    // function handleChange(e) {
    //     setPuzzleState({
    //         ...puzzleState,
    //         [e.target.name]: e.target.value
    //     })
    //     console.log(puzzleState)
    // }

    return (
        <div>
            <table>
                <tbody>

                    {
                        puzzle.content?.split('\n').map((row, index) => {
                            return <tr key={`inky-row-${index}`} >
                                <InkyRow puzzleState={puzzleState} row={row} handleChange={handleChange} upperIndex={index} />
                            </tr>
                        })
                    }
                </tbody>
            </table>
        </div>
    )
}
