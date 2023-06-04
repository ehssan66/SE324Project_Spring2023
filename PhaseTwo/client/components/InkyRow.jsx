import React from 'react'
import InkyCell from './InkyCell'
import { getName } from './Inky'

export default function InkyRow({ puzzleState, row, handleChange, upperIndex }) {

    function getClassName(position) {
        if (position.upper === '0' && position.lower === '0') return 'border-t-2 border-t-primary-content border-l-2 border-l-primary-content border-r-2 border-r-primary-content'
        if (position.upper === '0' && position.lower === '1') return 'border-t-2 border-t-primary-content border-l-2 border-l-primary-content border-b-2 border-b-primary-content'
        if (position.upper === '0' && position.lower === '2') return 'border-t-2 border-t-primary-content border-r-2 border-r-primary-content border-b-2 border-b-primary-content'
        if (position.upper === '1' && position.lower === '0') return 'border-l-2 border-l-primary-content border-r-2 border-r-primary-content'
        if (position.upper === '1' && position.lower === '1') return 'border-l-2 border-l-primary-content'
        if (position.upper === '1' && position.lower === '2') return 'border-r-2 border-r-primary-content'
        if (position.upper === '2' && position.lower === '0') return 'border-l-2 border-l-primary-content border-b-2 border-b-primary-content'
        if (position.upper === '2' && position.lower === '1') return 'border-b-2 border-b-primary-content border-t-2 border-t-primary-content border-r-2 border-r-primary-content'
        if (position.upper === '2' && position.lower === '2') return 'border-b-2 border-b-primary-content border-r-2 border-r-primary-content'
        return ''
    }

    return (
        <>
            {
                row.split(' ').map((cell, index) => {
                    let position = { upper: `${upperIndex}`, lower: `${index}` }
                    let className = getClassName(position);
                    return (
                        <td key={`inky-cell-${position.upper}--${position.lower}`} className={`border border-neutral relative ${className}`} >
                            <InkyCell position={position} puzzleState={puzzleState} onChange={handleChange} />
                            {
                                getName(position) === 'topLeft'
                                    ? <div className='absolute top-0 left-2 text-sm'>7 +</div>
                                    : getName(position) === 'top'
                                        ? <div className='absolute top-0 left-2 text-sm'>3 x</div>
                                        : getName(position) === 'center'
                                            ? <div className='absolute top-0 left-2 text-sm'>12 x</div>
                                            : null
                            }
                        </td>
                    )
                })
            }
        </>
    )
}
