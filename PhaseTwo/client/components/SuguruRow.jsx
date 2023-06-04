import React from 'react'
import SuguruCell from './SuguruCell'

export default function SuguruRow({ puzzleState, row, handleChange, upperIndex}) {

    function getClassName(position) {
        if (position.upper === '0' && position.lower === '0') return getRequiredBorders('top left right')
        if (position.upper === '0' && position.lower === '1') return getRequiredBorders('top bottom')
        if (position.upper === '0' && position.lower === '2') return getRequiredBorders('top bottom')
        if (position.upper === '0' && position.lower === '3') return getRequiredBorders('top right')
        if (position.upper === '0' && position.lower === '4') return getRequiredBorders('top left')
        if (position.upper === '0' && position.lower === '5') return getRequiredBorders('top bottom right')
        if (position.upper === '1' && position.lower === '0') return getRequiredBorders('left right')
        if (position.upper === '1' && position.lower === '1') return getRequiredBorders('left right bottom')
        if (position.upper === '1' && position.lower === '2') return getRequiredBorders('left right')
        if (position.upper === '1' && position.lower === '3') return getRequiredBorders('left right bottom')
        if (position.upper === '1' && position.lower === '4') return getRequiredBorders('left right')
        if (position.upper === '1' && position.lower === '5') return getRequiredBorders('left right bottom')
        if (position.upper === '2' && position.lower === '0') return getRequiredBorders('left right')
        if (position.upper === '2' && position.lower === '1') return getRequiredBorders('left bottom')
        if (position.upper === '2' && position.lower === '2') return 
        if (position.upper === '2' && position.lower === '3') return getRequiredBorders('right bottom')
        if (position.upper === '2' && position.lower === '4') return getRequiredBorders('left right')
        if (position.upper === '2' && position.lower === '5') return getRequiredBorders('left right')
        if (position.upper === '3' && position.lower === '0') return getRequiredBorders('left right')
        if (position.upper === '3' && position.lower === '1') return getRequiredBorders('left right ')
        if (position.upper === '3' && position.lower === '2') return getRequiredBorders('left right bottom')
        if (position.upper === '3' && position.lower === '3') return getRequiredBorders('left right')
        if (position.upper === '3' && position.lower === '4') return getRequiredBorders('left right bottom')
        if (position.upper === '3' && position.lower === '5') return getRequiredBorders('left right')
        if (position.upper === '4' && position.lower === '0') return getRequiredBorders('left right bottom')
        if (position.upper === '4' && position.lower === '1') return getRequiredBorders('left right')
        if (position.upper === '4' && position.lower === '2') return getRequiredBorders('left bottom')
        if (position.upper === '4' && position.lower === '3') return 
        if (position.upper === '4' && position.lower === '4') return getRequiredBorders('right bottom')
        if (position.upper === '4' && position.lower === '5') return getRequiredBorders('left right')
        if (position.upper === '5' && position.lower === '0') return getRequiredBorders('left bottom')
        if (position.upper === '5' && position.lower === '1') return getRequiredBorders('bottom')
        if (position.upper === '5' && position.lower === '2') return getRequiredBorders('right bottom')
        if (position.upper === '5' && position.lower === '3') return getRequiredBorders('left right bottom')
        if (position.upper === '5' && position.lower === '4') return getRequiredBorders('left bottom')
        if (position.upper === '5' && position.lower === '5') return getRequiredBorders('bottom right')

        return ''
    }

    function getRequiredBorders(string) {
        let borders = [];
        if (string.includes('top')) borders.push('border-t-2 border-t-primary-content')
        if (string.includes('bottom')) borders.push('border-b-2 border-b-primary-content')
        if (string.includes('left')) borders.push('border-l-2 border-l-primary-content')
        if (string.includes('right')) borders.push('border-r-2 border-r-primary-content')
        return borders.join(' ')
    }


  return (
    <>
        {
            row.split(' ').map((cell, index) => {
                let position = { upper: `${upperIndex}`, lower: `${index}` }
                let className = getClassName(position);
                return (
                    <td key={`suguru-cell-${position.upper}--${position.lower}`} className={`border border-neutral relative ${className}`} >
                        <SuguruCell position={position} puzzleState={puzzleState} onChange={handleChange} />
                    </td>
                )
            })
        }
    </>
  )
}
