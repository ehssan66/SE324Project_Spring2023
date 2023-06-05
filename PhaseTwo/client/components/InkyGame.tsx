"use client"
import { Magicpen, Math, Warning2, TickCircle } from 'iconsax-react'
import { useEffect, useState } from 'react'
import Inky from './Inky'

interface Puzzle {
    id: number,
    content: string
}

interface PuzzleState {
    topLeft: string,
    top: string,
    topRight: string,
    left: string,
    center: string,
    right: string,
    bottomLeft: string,
    bottom: string,
    bottomRight: string
}

export default function InkyGame() {
    const [puzzle, setPuzzle] = useState({} as Puzzle);
    const [solution, setSolution] = useState({} as Puzzle);

    const [isTrue, setIsTrue] = useState(false);
    const [checked, setChecked] = useState(false);

    const [puzzleState, setPuzzleState] = useState({
        topLeft: '',
        top: '',
        topRight: '',
        left: '',
        center: '',
        right: '',
        bottomLeft: '',
        bottom: '',
        bottomRight: ''
    } as PuzzleState
    )

    function handlePuzzleStateChange(e: { target: { name: any; value: any } }) {
        setPuzzleState({
            ...puzzleState,
            [e.target.name]: e.target.value
        })
    }

    const fetchPuzzle = async () => {
        setPuzzle({ id: 1, content: 'e e e\ne e e\ne e e' })
    }

    const solvePuzzle = async (id: number) => {
        const res = await fetch(`/api/puzzles/${id}/solve`)
        const data = await res.json();
        console.log(data["content"])
        setSolution({ id: 1, content: data["content"] })
    }

    const flipCard = () => {
        const card = document.querySelector('.flip-card')
        card?.classList.toggle('[transform:rotateY(180deg)]')
    }


    /**
     * Checks if the solution is correct by sending it to the server
     */
    const handleCheck = async () => {
        const solution = `${puzzleState.topLeft} ${puzzleState.top} ${puzzleState.topRight}\n${puzzleState.left} ${puzzleState.center} ${puzzleState.right}\n${puzzleState.bottomLeft} ${puzzleState.bottom} ${puzzleState.bottomRight}`

        // send solution to server
        const res = await fetch('/api/puzzles/2/check', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                content: solution,
            })
        })
        const data = await res.json()
        console.log(data)
        setIsTrue(data.success);
        setChecked(true);

        setTimeout(() => {
            setIsTrue(false);
            setChecked(false);
        }, 4000);

    }

    useEffect(() => {
        // fetch /api/puzzles and setImage
        fetchPuzzle()
        solvePuzzle(2)
    }, [])

    return (
        <div className='game flex flex-col lg:flex-row items-center justify-center h-full w-full m-auto'>
            {isTrue ?
                <div className='bg-success transition duration-700 bg-opacity-50 flex flex-col items-center justify-center absolute top-24 right-8'>
                    <h1 className='text-xl text-white px-14 py-2 flex items-center gap-2'>
                        <TickCircle size='24' />
                        Correct!
                    </h1>
                </div>
                : checked ?
                    <div className='bg-error transition duration-700 bg-opacity-50 flex flex-col items-center justify-center absolute top-24 right-8'>
                        <h1 className='text-xl text-white px-14 py-2 flex items-center gap-2'>
                            <Warning2 size='24' />
                            Incorrect!
                        </h1>
                    </div>
                    : <></>
            }
            <section className='flex-grow'></section>
            <section className='[perspective:1000px] w-[206px] max-w-[206px] px-6 lg:px-0 max-h-[calc(206px)] h-[206px]'>
                <div className='flip-card [transform-style:preserve-3d] transition-transform duration-[0.8s] relative w-full h-full'>
                    <div className='puzzle absolute w-full h-full [-webkit-backface-visibility:hidden] [backface-visibility:hidden] flex items-center justify-center'>
                        <Inky puzzle={puzzle} puzzleState={puzzleState} handleChange={handlePuzzleStateChange} />
                    </div>
                    <div className='solution absolute w-full h-full [transform:rotateY(180deg)] [-webkit-backface-visibility:hidden] [backface-visibility:hidden]'>
                        <div>
                            <table>
                                <tbody>
                                    <tr>
                                        <td className={`border border-neutral relative border-t-2 border-t-primary-content border-l-2 border-l-primary-content border-r-2 border-r-primary-content`} >
                                            <div>
                                                <input type="text" value="2" maxLength={1}
                                                    className='text-center bg-base-100 p-6 w-16 h-16' />
                                            </div>
                                            <div className="absolute top-0 left-2 text-sm">7 +</div>
                                        </td>
                                        <td className={`border border-neutral relative border-t-2 border-t-primary-content border-l-2 border-l-primary-content border-b-2 border-b-primary-content`} >
                                            <div>
                                                <input type="text" value="3" maxLength={1}
                                                    className='text-center bg-base-100 p-6 w-16 h-16' />
                                            </div>
                                            <div className="absolute top-0 left-2 text-sm">3 x</div>

                                        </td>
                                        <td className={`border border-neutral relative border-t-2 border-t-primary-content border-r-2 border-r-primary-content border-b-2 border-b-primary-content`} >
                                            <div>
                                                <input type="text" value="1" maxLength={1}
                                                    className='text-center bg-base-100 p-6 w-16 h-16' />
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td className={`border border-neutral relative border-l-2 border-l-primary-content border-r-2 border-r-primary-content`} >
                                            <div>
                                                <input type="text" value="1" maxLength={1}
                                                    className='text-center bg-base-100 p-6 w-16 h-16' />
                                            </div>
                                        </td>
                                        <td className={`border border-neutral relative border-l-2 border-l-primary-content`} >
                                            <div>
                                                <input type="text" value="2" maxLength={1}
                                                    className='text-center bg-base-100 p-6 w-16 h-16' />
                                            </div>
                                            <div className="absolute top-0 left-2 text-sm">12 x</div>
                                        </td>
                                        <td className={`border border-neutral relative border-r-2 border-r-primary-content`} >
                                            <div>
                                                <input type="text" value="3" maxLength={1}
                                                    className='text-center bg-base-100 p-6 w-16 h-16' />
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td className={`border border-neutral relative border-l-2 border-l-primary-content border-b-2 border-b-primary-content`} >
                                            <div>
                                                <input type="text" value="3" maxLength={1}
                                                    className='text-center bg-base-100 p-6 w-16 h-16' />
                                            </div>
                                        </td>
                                        <td className={`border border-neutral relative border-b-2 border-b-primary-content border-t-2 border-t-primary-content border-r-2 border-r-primary-content`} >
                                            <div>
                                                <input type="text" value="1" maxLength={1}
                                                    className='text-center bg-base-100 p-6 w-16 h-16' />
                                            </div>
                                        </td>
                                        <td className={`border border-neutral relative border-b-2 border-b-primary-content border-r-2 border-r-primary-content`} >
                                            <div>
                                                <input type="text" value="2" maxLength={1}
                                                    className='text-center bg-base-100 p-6 w-16 h-16' />
                                            </div>
                                        </td>
                                    </tr>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

            </section>
            <section className='flex flex-col items-center justify-center flex-grow gap-8 pt-12 lg:pt-0'>
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