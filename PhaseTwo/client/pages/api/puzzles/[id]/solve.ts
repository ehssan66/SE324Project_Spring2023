import type { NextApiRequest, NextApiResponse } from 'next'

interface Data {
  content: string
}

export default async function handler(
  req: NextApiRequest,
  res: NextApiResponse<Data>
) {
  const { type, id } = req.query;
  const response = await fetch(`http://localhost:8080/api/puzzles/${id}/solve`)
  const data: Data = await response.json();
  res.status(200).json(data);

}
