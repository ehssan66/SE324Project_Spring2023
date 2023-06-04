"use client"
import { Add, Magicpen, Math } from 'iconsax-react'
import Image from 'next/image'
import { useEffect, useState } from 'react'
import Suguru from './Suguru'

type Puzzle = {
    id: number,
    content: string
}

type PuzzleState = {
    zeroZero: string,
    zeroOne: string,
    zeroTwo: string,
    zeroThree: string,
    zeroFour: string,
    zeroFive: string,
    oneZero: string,
    oneOne: string,
    oneTwo: string,
    oneThree: string,
    oneFour: string,
    oneFive: string,
    twoZero: string,
    twoOne: string,
    twoTwo: string,
    twoThree: string,
    twoFour: string,
    twoFive: string,
    threeZero: string,
    threeOne: string,
    threeTwo: string,
    threeThree: string,
    threeFour: string,
    threeFive: string,
    fourZero: string,
    fourOne: string,
    fourTwo: string,
    fourThree: string,
    fourFour: string,
    fourFive: string,
    fiveZero: string,
    fiveOne: string,
    fiveTwo: string,
    fiveThree: string,
    fiveFour: string,
    fiveFive: string
}

export default function Game() {
    const [puzzle, setPuzzle] = useState({} as Puzzle);
    const [solution, setSolution] = useState({} as Puzzle);

    const [isTrue, setIsTrue] = useState(false);
    const [checked, setChecked] = useState(false);

    const [puzzleState, setPuzzleState] = useState({
        zeroZero: '',
        zeroOne: '',
        zeroTwo: '',
        zeroThree: '',
        zeroFour: '',
        zeroFive: '',
        oneZero: '',
        oneOne: '',
        oneTwo: '',
        oneThree: '',
        oneFour: '',
        oneFive: '',
        twoZero: '',
        twoOne: '',
        twoTwo: '',
        twoThree: '',
        twoFour: '',
        twoFive: '',
        threeZero: '',
        threeOne: '',
        threeTwo: '',
        threeThree: '',
        threeFour: '',
        threeFive: '',
        fourZero: '',
        fourOne: '',
        fourTwo: '',
        fourThree: '',
        fourFour: '',
        fourFive: '',
        fiveZero: '',
        fiveOne: '',
        fiveTwo: '',
        fiveThree: '',
        fiveFour: '',
        fiveFive: ''
    } as PuzzleState)

    function handlePuzzleStateChange(e: { target: { name: any; value: any } }) {
        setPuzzleState({
            ...puzzleState,
            [e.target.name]: e.target.value
        })
        console.log(puzzleState)
    }

    const fetchPuzzle = async () => {
        // const res = await fetch('/api/puzzles/suguru/random')
        // const data = await res.json();
        // console.log(data);
        // setPuzzle(data);
        // const card = document.querySelector('.flip-card')
        // if(card?.classList.contains('[transform:rotateY(180deg)]')) {
        //     card?.classList.remove('[transform:rotateY(180deg)]')
        // }
        // solvePuzzle(data.id);

        // setPuzzle({ id: 1, content: 'e e e\ne e e\ne e e' })
    }

    const solvePuzzle = async (id: number) => {
        const res = await fetch(`/api/puzzles/suguru/${id}/solve`)
        const data = await res.json();
        setSolution(data)
    }

    const flipCard = () => {
        const card = document.querySelector('.flip-card')
        card?.classList.toggle('[transform:rotateY(180deg)]')
    }

    /*
    *   endpoint needs to be checked later
    */
    const handleCheck = async () => {
        const solution = `
        ${puzzleState.zeroZero}a${puzzleState.zeroOne}b${puzzleState.zeroTwo}b${puzzleState.zeroThree}b${puzzleState.zeroFour}c${puzzleState.zeroFive}c\n
        ${puzzleState.oneZero}a${puzzleState.oneOne}d${puzzleState.oneTwo}e${puzzleState.oneThree}b${puzzleState.oneFour}c${puzzleState.oneFive}f\n
        ${puzzleState.twoZero} a${puzzleState.twoOne}e${puzzleState.twoTwo}e${puzzleState.twoThree}e${puzzleState.twoFour}c${puzzleState.twoFive}g\n
        ${puzzleState.threeZero}a${puzzleState.threeOne}h${puzzleState.threeTwo}e${puzzleState.threeThree}i${puzzleState.threeFour}c${puzzleState.threeFive}g\n
        ${puzzleState.fourZero}i${puzzleState.fourOne}i${puzzleState.fourTwo}i${puzzleState.fourThree}i${puzzleState.fourFour}i${puzzleState.fourFive}g\n
        ${puzzleState.fiveZero}h${puzzleState.fiveOne}h${puzzleState.fiveTwo}h${puzzleState.fiveThree}i${puzzleState.fiveFour}g${puzzleState.fiveFive}g
        `
        console.log(solution)

        // send solution to server
        const res = await fetch('/api/puzzles/1/check', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                solution,
            })
        })
        const data = await res.json()
        console.log(data)
        setIsTrue(data.success);
        setChecked(true);
    }

    useEffect(() => {
        // fetch /api/puzzles and setImage
        fetchPuzzle()
    }, [])

    return (
        <div className='game flex flex-col lg:flex-row items-center justify-center h-full w-full m-auto'>
            <section className='flex-grow'></section>
            <section className='[perspective:1000px] w-[500px] max-w-[100vw] px-6 lg:px-0 max-h-[calc(100vw-3rem)] h-[500px]'>
                <div className='flip-card [transform-style:preserve-3d] transition-transform duration-[0.8s] relative w-full h-full'>
                    <div className='puzzle absolute w-full h-full [-webkit-backface-visibility:hidden] [backface-visibility:hidden] flex items-center justify-center'>
                        {/* { puzzle?.link && 
                        <Image src={`http://localhost:8080/${puzzle.link}`} width='500' height='500' alt={''}></Image>
                    } */}
                        <Suguru puzzle={'3a b b b c c\na 1d e 2b 3c 1f\na e e e c g\na h e i c g\na h i i i g\nh h h i 3g g'} puzzleState={puzzleState} setPuzzleState={setPuzzleState} handleChange={handlePuzzleStateChange}/>
                    </div>
                    <div className='solution absolute w-full h-full [transform:rotateY(180deg)] [-webkit-backface-visibility:hidden] [backface-visibility:hidden]'>
                        {/* { solution?.link && 
                        <Image src={`http://localhost:8080/${solution.link}`} width='500' height='500' alt={''}></Image>
                    } */}
                    </div>
                </div>

                {isTrue ?
                    <div className='bg-green-500 bg-opacity-50 flex flex-col items-center justify-center'>
                        <h1 className='text-4xl text-white p-4'>Correct!</h1>
                    </div>
                    : checked ?
                        <div className='bg-red-500 bg-opacity-50 flex flex-col items-center justify-center'>
                            <h1 className='text-4xl text-white p-4'>Incorrect!</h1>
                        </div>
                        : <></>
                }

            </section>
            <section className='flex flex-col items-center justify-center flex-grow gap-8 pt-12 lg:pt-0'>
                <button onClick={fetchPuzzle} className="btn new-puzzle w-full lg:w-3/4 gap-2 text-primary-content">
                    <Add size="24" />
                    New Puzzle
                </button>
                <button onClick={handleCheck} className="btn new-puzzle w-full lg:w-3/4 gap-2 text-primary-content">
                    <Math size="24" />
                    Check Solution
                </button>
                <button onClick={flipCard} className="btn solve-puzzle w-full lg:w-3/4 gap-2 text-primary-content">
                    <Magicpen size="24" />
                    Solve
                </button>
            </section>
        </div>
    );
}